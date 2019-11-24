#Формат запросов и ответов: JSON

Название строки запроса: json

###Запрос для регистрации/авторизации:
<pre>
URL:
    авторизация: /authentication
    регистрация: /registration
    
Method: POST
{
	login: login,
	password: password
}
</pre>

###Ответ регистрации/авторизации:
<pre>
{
	status: Auth/Registered/No_Auth/No_registered
	message: string
}
</pre>

###Запрос для проверки попадания точки в область:
<pre>
URL: /add_point
Method: POST
{
	x: x,
	y: y,
	r: r
	var: 44 (var of lab)
}
</pre>

###Ответ от сервера:
<pre>
{
	Points[] arr, point: x, y, r, in, correct
}
</pre>

###Запрос для выхода:
<pre>
URL: /exit
Method: POST
{
	exit: true
}
</pre>

###Ответ для выхода:
<pre>
{
	status: Exit/No_exit
	message: string
}
</pre>