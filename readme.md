#Jwt POC Demo Application

---
## End Points

<table>
    <tr>
        <td>Acuator</td>
        <td>https://127.0.0.1/jwt/poc/actuator</td>
    </tr>
    <tr>
        <td>Swagger API</td>
        <td>https://127.0.0.1/jwt/poc/swagger-ui/index.html</td>
    </tr>
    <tr>
        <td>Base URL</td>
        <td>https://127.0.0.1/jwt/poc</td>
    </tr>
</table>

---
## Howto Build / Run Application

> ###*Executed from CLI*
>       package application - mvn clean compile test package 
>       Create Docker image - docker build -t jwt/poc:1.0.0 . 
>       instantiate Image   - docker run -d -p 443:443 jwt/poc:1.0.0


---
##Steps to follow access API Enpoints

> ###*Important Info - Authentication credentials*
>        username: admin\
>        password: admin

> ###*Postman Request to get Authorization Token*
>```java
>OkHttpClient client = new OkHttpClient().newBuilder()
>        .build();
>        MediaType mediaType = MediaType.parse("application/json");
>        RequestBody body = RequestBody.create(mediaType, "{\r\n    \"username\":\"admin\",\r\n    \"password\":\"admin\"\r\n}");
>        Request request = new Request.Builder()
>        .url("https://127.0.0.1/jwt/poc/authenticate")
>        .method("POST", body)
>        .addHeader("Content-Type", "application/json")
>        .build();
>        Response response = client.newCall(request).execute();
>```

> ###*Postman request Using Token in request*
>```java
>OkHttpClient client = new OkHttpClient().newBuilder()
>        .build();
>        MediaType mediaType = MediaType.parse("text/plain");
>        RequestBody body = RequestBody.create(mediaType, "");
>        Request request = new Request.Builder()
>        .url("https://127.0.0.1/jwt/poc/about")
>        .method("GET", body)
>        .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJmb28iLCJleHAiOjE2NjI1MTE0OTksImlhdCI6MTY2MjI5NTQ5OX0.u4HY79LjN8HoYzkZO7YIPwZVPOC8vWsoS3gyk_Nr-F8")
>        .build();
>        Response response = client.newCall(request).execute();
>```

---
## Notes on Actuator access / implementation

[Actuator Info End Point](https://127.0.0.1/jwt/poc/actuator)

Usage:

![img_5.png](img_5.png)

---
## Notes on Swagger access / implementation

[SwaggerUI End Point](htts://127.0.0.1/jwt/poc/swagger-ui/index.html)

>##Imporat Info....
> *Need to acquire authentication token via 'authenticate' endpoint
> prior to calling endpoints requiring authentication specifically
> JWT token*
> 
> NB!!! Prefix JWT token acquired with 'Bearer ' in authorization 
> text block ("Text box presented when clicking authorization button
> top right of swagger UI")

*Example Screens.......*

![img.png](img.png)
![img_3.png](img_3.png)
![img_2.png](img_2.png)
![img_4.png](img_4.png)

---
## Notes on Http2 access / implementation

```
Info: Java 11+ neededto facilitate Http2 protocol
```

###Need to update.....

pom.xml
```properties
<properties>
    <java.version>11</java.version>
</properties>
```

application.yml
```yaml
server:
  # Will give us compressed headers & binary payload + faster through put
  http2:
    enabled: true
```

---
## Notes on SSL used / implementation

Execute CMD to generate Self Signed Certificate
```java
openssl req -x509 -newkey rsa:4096 -keyout key.pem -out cert.pem -sha256 -days 365
```

*Output (Question/Response) in generating elf signed certificate*
```
Generating a 4096 bit RSA private key
.............................................................................................................................................................................................................................................................................................................................++++
.............................++++
writing new private key to 'key.pem'
Enter PEM pass phrase:
Verifying - Enter PEM pass phrase:
-----
You are about to be asked to enter information that will be incorporated
into your certificate request.
What you are about to enter is what is called a Distinguished Name or a DN.
There are quite a few fields but you can leave some blank
For some fields there will be a default value,
If you enter '.', the field will be left blank.
-----
Country Name (2 letter code) [AU]:ZA
State or Province Name (full name) [Some-State]:WC
Locality Name (eg, city) []:Cape Town
Organization Name (eg, company) [Internet Widgits Pty Ltd]:Rain
Organizational Unit Name (eg, section) []:IT
Common Name (e.g. server FQDN or YOUR name) []:Demo
Email Address []:henryweber.sa@gmail.com
```
*create a PKCS#12 file using OpenSSL*
```
openssl pkcs12 -export -in cert.pem -inkey key.pem -out rain.p12
```
*convert it to JKS using keytool -importkeystore*
```
keytool -importkeystore -srckeystore rain.p12 -destkeystore rain.jks -deststoretype pkcs12
```
---
## Notes on Project start / FrameworkSSL used

>Used -  https://start.spring.io

---
## Notes on Project Banner creation

>Link - https://devops.datenkollektiv.de/banner.txt/index.html
