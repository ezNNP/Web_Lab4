#Формат запросов и ответов сервера

###Регистрация
<pre>
curl -H "Content-Type: application/json" -H "Accept: application/json" 
-d '{"login": "login", "password": "password"}' 
-X POST http://localhost:8080/auth/register

URL: /auth/register
Headers:
    Content-Type: application/json
    Accept: application/json
Method: POST
JSON REQUEST:
{
    "login": "login", "password": "password"
}
JSON RESPONSE:
{"login":"login","status":"REGISTERED"}
JSON ERROR RESPONSE (duplicate user):
{"timestamp":"2019-12-13T14:49:00.360+0000","status":403,"error":"Forbidden","message":"Access Denied","path":"/auth/register"}
</pre>

###Авторизация
<pre>
curl -H "Content-Type: application/json" -H "Accept: application/json" 
-d '{"login": "login", "password": "password"}' 
-X POST http://localhost:8080/auth/login

URL: /auth/login
Headers:
    Content-Type: application/json
    Accept: application/json
Method: POST
JSON REQUEST:
{
    "login": "login", "password": "password"
}
JSON RESPONSE:
{"login":"login","status":"OK","token":"JWT_TOKEN"}
JSON ERROR RESPONSE:
{"timestamp":"2019-12-13T14:51:32.734+0000","status":403,"error":"Forbidden","message":"Access Denied","path":"/auth/login"}
</pre>

###Добавление точки
<pre>
curl -H "Authorization: Bearer_JWT_TOKEN" 
-H "Accept: application/json" -H "Content-Type: application/json" 
-d '{"x": 0, "y": 0, "r": 2}' 
-X POST http://localhost:8080/points/add_point
URL: /points/add_point
Headers:
    Authorization: Bearer_JWT_TOKEN
    Content-Type: application/json
    Accept: application/json
Method: POST
JSON REQUEST:
{"x": 0, "y": 0, "r": 2}
JSON RESPONSE
{"points":[{"id":7,"x":0.0,"y":0.0,"r":2.0,"hit":true,"correct":true,"owner":null},{"id":6,"x":0.0,"y":0.0,"r":2.0,"hit":true,"correct":true,"owner":null},{"id":5,"x":0.0,"y":0.0,"r":2.0,"hit":true,"correct":true,"owner":null},{"id":4,"x":0.0,"y":0.0,"r":2.0,"hit":true,"correct":true,"owner":null},{"id":3,"x":0.0,"y":0.0,"r":2.0,"hit":true,"correct":true,"owner":null},{"id":2,"x":0.0,"y":0.0,"r":2.0,"hit":true,"correct":true,"owner":null},{"id":1,"x":0.0,"y":0.0,"r":2.0,"hit":true,"correct":true,"owner":null}]}
JSON ERROR RESPONSE
in progress
</pre>

IN PROGRESS