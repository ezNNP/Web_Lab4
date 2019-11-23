<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>start vue</title>
</head>
<body>
<script src="/js/vue.js"></script>
<div id="app">
    <h1>Пошел нахуй {{name}}</h1>
    <input type="text" v-on:input="changeName">
</div>

<script>
    new Vue({
        el:'#app',
        data: {
            name: 'Vue'
        },
        methods: {
            changeName: function (event) {
                this.name = event.target.value
            }
        }
    })
</script>
</body>
</html>