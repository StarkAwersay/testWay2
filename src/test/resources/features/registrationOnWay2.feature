# language: ru
Функция: Тест на регистрацию на сайте Way2Automation

  Предыстория:
    Дано Страница с регистрацией "https://sso.teachable.com/secure/673/identity/sign_up/with_email"

  @success
  Сценарий: Успешная регистрация
    Когда Пользователь заполняет данные: логин "Ilya Ponomarev", пароль"12345688s" и email "4frankyuIlsszzlya@gmail.com", наажимает на кнопку Agree и кнопку Sing up
    Тогда Появляется меню с профилем

  @fail
  Сценарий: Безуспешная регистрация
    Если Уже существует пользователь с такими данными: логин "Ilya Ponomarev", пароль"12345688s" и email "4frankyBigshapsada145saha1ssa1w1s111sdt@gmail.com"
    То Высвечивается ошибка, что почта уже используется

