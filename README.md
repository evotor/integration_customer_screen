# integration_customer_screen

Данная библиотека предназначена для интеграции вашего приложения в эвотор как экрана покупателя.

Внешнее интеграционное приложение может отправить запрос на взаимодействие с определенным приложением. После этого будет вызван метод execute(String) с параметром, пришедшим из внешнего интеграционного приложения. Содержимое параметра передаётся без изменений. Существует ограничение на размер данных - 1МБ. В рамках сервиса вы можете открыть activity переопределенным методом startActivity(Intent), activity будет открыта также, как она могла бы быть открыта по тапу пользователя по иконке приложения (с флагом NEW_TASK). Метод startActivity откроет activity только после того, как метод execute(String) вернет результат.


    class MainService : CustomerScreenService() {
        override fun execute(request: String?): String {
            println("request = $request") // прочитайте входные параметры, используйте их. формат параметра - на ваше усмотрение
            Thread.sleep(2000) // допускаются длительные операции, но не более 10 секунд
            startActivity(Intent(this@MainService.applicationContext, MainActivity::class.java)) // есть возможность запустить стороннюю activity
            return "response" // верните ответ, формат ответа - на ваше усмотрение
        }
    }


Ваш сервис должен быть объявлен в AndroidManifest с intent-filter, улавливающим action "ru.evotor.integration.APP_REQUEST".
