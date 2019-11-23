#Формат запросов и ответов: JSON

###Запрос для регистрации/авторизации:
<pre>
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