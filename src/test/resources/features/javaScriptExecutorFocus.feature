# language: ru
Функция: Тест javascriptExecutor на смену фокуса

  Предыстория:
    Дано Страница с поисковой строкой

  @success
  Сценарий: Успешная смена фокуса
    Когда Пользователь нажимает на поисковую строку и с помощью javascriptExecutor убирается фокус с поисковой строки
    Тогда Происходит проверка того, что фокус сменился

  @fail
  Сценарий: Неуспешная смена фокуса
    Если Пользователь нажимает поисковую строку и фокус не убирается
    То Выводится ошибка о том, что фокус не изменился