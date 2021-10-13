package org.mymafia.mafiaGame.service.kakaoService;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

@Service
public class KakaoAPI {

    //access token을 카카오에서 얻기위한 메서드
    public String getAccessToken(String authorize_code){
        String access_token = "";
        String refresh_token = "";
        String requestURL = "https://kauth.kakao.com/oauth/token";

        try{
            //url객체 생성
            URL url = new URL(requestURL);
            // 정해진 URL 주소로 객체 연결
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            //post 요청을 하기 위해 기본값이 false 인 setDoOutput을 true로
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            //post 요청에 필요로 요구하는 파라미터를 스트림을 통해 요청을 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=9a58734637b33bc4afab88cc9e71fe77");
            sb.append("&redirect_uri=http://localhost:8080/login");
            sb.append("&code=" + authorize_code);
            bw.write(sb.toString());
            bw.flush();
            
            //결과 코드가 200 이면 성공
            int responseCode = connection.getResponseCode();
            System.out.println("responseCode: " + responseCode);

            // 요청을 통해 얻은 JSON 타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            String result = "";

            while((line = br.readLine()) != null){
                result += line;
            }
            System.out.println("response Body: " + result);

            // Gson 라이브러리에서 JSON parser 객체 생성
             JsonParser parser = new JsonParser();
             JsonElement element = parser.parse(result);

             access_token = element.getAsJsonObject().get("access_token").getAsString();
             refresh_token = element.getAsJsonObject().get("refresh_token").getAsString();

             System.out.println("access_token : " + access_token);
             System.out.println("refresh_token : " + refresh_token);

             br.close();
             bw.close();

        }catch (IOException e){
            e.printStackTrace();
        }
        return access_token;
    }

    public HashMap<String, Object> getUserInfo(String access_token){
        // 요청하는 클라이언트마다 가진 정보가 다를 수 있어 HashMap 타입으로 선언
        HashMap<String, Object> userInfo = new HashMap<>();
        String requestURL = "https://kapi.kakao.com/v2/user/me";

        try {
            URL url = new URL(requestURL);
            HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
            //기본적으로 HttpsURLConnection은 GET 방식을 사용한다.
            connection.setRequestMethod("POST");
            // 요청에 필요한 Header 에 포함될 내용
            connection.setRequestProperty("Authorization", "Bearer" + access_token);

            int responseCode = connection.getResponseCode();
            System.out.println("responseCode: " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line ="";
            String result ="";

            while((line = br.readLine()) != null){
                result += line;
            }

            System.out.println("responseBody : " + result);

            JsonParser jsonParser = new JsonParser();
            JsonElement element = jsonParser.parse(result);
            JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
            JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

            String nickname = properties.getAsJsonObject().get("nickname").getAsString();
            String email = kakao_account.getAsJsonObject().get("email").getAsString();

            userInfo.put("nickname",nickname);
            userInfo.put("email",email);


        }catch (IOException e){
            e.printStackTrace();
        }
        return userInfo;
    }
}
