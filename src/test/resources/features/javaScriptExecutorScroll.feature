# language: ru
Функция: Тест javascriptExecutor на смену фокуса

  @success
  Сценарий: Успешная скролл страницы
    Дано Главная страница яндекса
    Когда Пользователь скроллит страницу на "150" пикселей
    Тогда Происходит проверка того, что страница проскролилась на "150"

  @fail
  Сценарий: Страница не проскроллилась
    Дано Главная страница яндекса
    Если Если страница не просролилась
    То Выводится ошибка о том, что страница не проскроллилась