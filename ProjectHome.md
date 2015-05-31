ВКонтакте OAuth2 логин для сайта написанного на Java/Servlet технологии.

В проекте есть три модуля:
1. Модуль vk-java-login-oauth-auth который даёт библиотеку для томкета с вальве.
2. Вар приложение. (с сервлетом для редиректа)
3. Сборка томкета. (с собранными варом и джаром)

Настройка вашего томкета:
положите либу vk-java-login-oauth-auth-1.1-SNAPSHOT.jar в томкет/lib
настройте web.xml в вашем варе.

Документация ВКонтакта http://vkontakte.ru/developers.php?oid=-1&p=%D0%90%D0%B2%D1%82%D0%BE%D1%80%D0%B8%D0%B7%D0%B0%D1%86%D0%B8%D1%8F

![http://vk-java-login-oauth.googlecode.com/files/Screenshot.png](http://vk-java-login-oauth.googlecode.com/files/Screenshot.png)