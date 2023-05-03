# language: ru
Функция: Тест на авторизацию на сайте PracticeSite2

  @success
  Сценарий: Успешная авторизация
    Дано Страница для авторизации "https://www.way2automation.com/angularjs-protractor/registeration/#/login"
    Когда Пользователь вводит данные: login"angular", password"password", description"description" и нажимает кнопку Login
    Тогда Происходит проверка того, что авторизация прошла успешно

  @fail
  Сценарий: Некорректный ввод логина
    Дано Страница для авторизации "https://www.way2automation.com/angularjs-protractor/registeration/#/login"
    Когда Пользователь вводит данные с некорректный логином: login"angularr", password"password", description"description" и нажимает кнопку Login
    Тогда Высвечивается ошибка, что введённые логин или пароль неверны

  @fail
  Сценарий: Некорректный ввод пароля
    Дано Страница для авторизации "https://www.way2automation.com/angularjs-protractor/registeration/#/login"
    Когда Пользователь вводит данные с некорректным паролем: login"angular", password"passwrd", description"description" и нажимает кнопку Login
    Тогда Высвечивается ошибка, что введённые логин или пароль неверны

  @fail
  Сценарий: Некорректный ввод данных в поле 'description'
    Дано Страница для авторизации "https://www.way2automation.com/angularjs-protractor/registeration/#/login"
    Когда Пользователь вводит данные с некорректными данными для поля 'description': login"angular", password"passwrd", description"de" и нажимает кнопку Login
    Тогда Кнопка Login не активна, а поле 'description' подсвечивается краснымм цветом
