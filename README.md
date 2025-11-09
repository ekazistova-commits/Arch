# Ktor multiplatform architecture

## План семинара:

- Управление состоянием
- Clean Architecture и модульное приложение
- Модули `StateMachine`

## Ссылки:

- [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
- [Kodein DI](https://kosi-libs.org/kodein/7.28/core/injection-retrieval.html)
- [CommonStateMachine](https://github.com/motorro/CommonStateMachine)

## Запуск сервера:

```bash
./gradlew :server:run
```

## Запуск node-приложения:

```bash
./gradlew :jsApp:assemble
cd jsApp/node/
npm start
```

## ДЗ 1. Чистая архитектура
В этом задании вам предстоит воспользоваться подходом Чистой архитектуры для абстракции модуля 
`shared` от реализации сетевого интерфейса:

- Поместите определение [интерфейсов usecase](shared/src/commonMain/kotlin/ru/otus/arch/net/usecase)
  в доменный модуль.
- Создайте отдельный модуль приложения для реализации usecase
- Перенесите определения API и usecase в новый модуль
- Создайте новый DI-модуль для сетевых компонентов и перенесите [определения интерфейсов](shared/src/commonMain/kotlin/ru/otus/arch/di/SharedModule.kt)
  в модуль реализации.
- Подключите зависимость на новый модуль реализации в [основной модуль приложения](composeApp/build.gradle.kts)
- Подключите DI-модуль реализации в [основной модуль приложения](composeApp/src/commonMain/kotlin/ru/otus/arch/di/AppModule.kt)
- Подключите зависимость на новый модуль реализации в [js-модуль приложения](jsApp/build.gradle.kts)
- Подключите DI-модуль реализации в [js-модуль приложения](jsApp/src/webMain/kotlin/ru/otus/arch/di/AppModule.kt)

## ДЗ 2. Управление состоянием*
**НЕ ДЛЯ РЕАЛЬНОГО ПРИМЕНЕНИЯ! В УЧЕБНЫХ ЦЕЛЯХ!**
В этом задании вам предстоит попробовать создать собственное состояние `CommonMachineState` для 
напоминания логина и пароля:

- В [жесты](basicauth/src/commonMain/kotlin/ru/otus/arch/basicauth/data/BasicAuthGesture.kt) модуля 
  авторизации добавлен жест `ForgotPassword`.
- В [состояния экрана](basicauth/src/commonMain/kotlin/ru/otus/arch/basicauth/data/BasicAuthUiState.kt)
  модуля авторизации добавлено состояние `ForgotPassword`.
- Реализуйте новое состояние `ForgotPasswordState`, в которое будет переходить основное состояние модуля
  при нажатии на кнопку `Forgot password`.
- Сохраняйте поле ошибки при переходах на экран напоминания и обратно.
- Обновляете состояние экрана в `ForgotPassword` с **хардкодными** значениями имени пользователя и пароля.
  Значения полей можно взять из [констант сервера](server/src/main/kotlin/ru/otus/arch/server/Constants.kt).
  Никаких механизмов чтения файлов делать не нужно! Просто скопируйте текст.
- Обрабатывайте жест `BasicAuthGesture.Back` и возвращайтесь на основной экран модуля.
- Обрабатывайте жест `BasicAuthGesture.Action` и возвращайтесь на основной экран модуля.
 

