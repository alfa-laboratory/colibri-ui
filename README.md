<img align="left" width="140" height="140" title="colibri-ui"
     src="https://rawgit.com/alfa-laboratory/colibri-ui/master/collibri_logo.svg" />
     
# Colibri-ui
[![Build Status](https://travis-ci.org/alfa-laboratory/colibri-ui.svg?branch=master)](https://travis-ci.org/alfa-laboratory/colibri-ui)
[![Coverage Status](https://coveralls.io/repos/github/alfa-laboratory/colibri-ui/badge.svg?branch=master)](https://coveralls.io/github/alfa-laboratory/colibri-ui?branch=master)
[![Download](https://api.bintray.com/packages/alfa-laboratory/maven-releases/colibri-ui/images/download.svg) ](https://bintray.com/alfa-laboratory/maven-releases/colibri-ui/_latestVersion)

[![@colibri_ui_community](https://img.shields.io/badge/Telegram-%40colibri__ui__community-orange.svg)](https://t.me/colibri_ui_community) ⇦ Join us!

Библиотека для автоматизации ui-тестирования мобильных приложений

## Для чего мы это сделали?
Решение создано для упрощения написания сценариев ui-тестов для мобильных приложений, для сохранения единообразия подходов к описанию процессов в едином стиле. 

## Папки проекта:
* *enviroment* - содержит описания девайсов и нод для запуска фреймворка на устройствах
* *pages* - содержит описания экранов приложений 
* *planTestCycle* - содержит перечень флагов для фильтрации story по meta-данным
```
плюс <имя метки> - добавить story в выборку
минус <имя метки> - исключить story в выборку
```
* *stories* - папка для размещения сценариев
* *users* - описание юзеров

## Локальный запуск и запуск на CI

Локальный запуск осуществляется на порту 4723.
Запуск на CI в конфигурации с хабом запускается на порту хаба - 5566.
Порт общения с девайсом задается в файлах /environment/def/environment*.properties


## Для запуска хаба:
1) заходим в корень проекта, папка в которой лежит build.gradle;
2) в консоли выполняем
```
sh scripts/start_hub.sh
```

Результат удачного запуска: появился файл seleniumhub.pid в папке scripts и соответствующие логи в консоли.

## Для запуска ноды:
1) заходим в корень проекта, папка в которой лежит build.gradle;
2) в консоли выполняем 'sh scripts/start_node.sh &lt;запускаемая_платформа&gt; &lt;порт&gt;'
Например,
```
sh scripts/start_node.sh ios10 4726
sh scripts/start_node.sh android5 4721
```

Результат удачного запуска: появился файл appium.pid в папке ресурсов соответствующей платформы и логи в консоли.

## Для остановки ноды (пока не работает):
1) Заходим в корень проекта, папка в которой лежит build.gradle;
2) В консоли выполняем 
```
sh scripts/stop.sh &lt;платформа&gt;
```

Результат удачного запуска: исчез файл appium.pid в папке ресурсов соответствующей платформы и есть логи в консоли об остановке процесса

## Пример команды для запуска проекта на локальном ПК (MAC):
```
1) ./gradlew --info clean test --tests "*AndroidStories*" -Pplatform=android5 -Puser=6016680 -PtestType=smoke -PbuildVersion=8.4.0.0-SNAPSHOT
2) ./gradlew --info clean test --tests "*AndroidStories*" -Pplatform=android4 -Puser=5773935 -PtestType=smoke -PbuildVersion=8.4.0.0-SNAPSHOT
3) ./gradlew --info clean test --tests "*IOSStories*" -Pplatform=ios10.2 -Puser=1907306 -PtestType=regress -PbuildVersion=8.3_7426
```
## Команды для быстрого уничтожения кластера в системе 
* pkill -f selenium
* pkill -f appium

## Добавление устройства

Для автотестирования на новом (который не был добавлен ранее) устройстве, добавьте новую папку в раздел проекта по аналогии с существующими
```
/src/test/resources/environment
```
В файле device.properties нужно прописать имя устройства, его udid и порт. первые два параметра можно найти в окне Window->Devices в Xcode для iOS-устройств. Порт присваивается икрементально от последнего добавленного.
Стоит обратить внимание, что добавленное устройство iOS также должно быть добавлено в сертификат разработки - добавить могут ios-разработчики команды. 



## Подпись драйвера Appium (iOS)

В случае ios, нужно переподписать приложение драйвера и раннера appium-а сертификатом ios-разработчика. Для этого нужно быть добавленным сертификат разработчиков - добавить могут ios-разработчики команды. 
По данном адресу
/usr/local/lib/node_modules/appium/node_modules/appium-xcuitest-driver/WebDriverAgent/
Необходимо открыть при помощи xcode файл с расширением .xcodeproj, выбрать верхний пункт в левой панели и установить Team в двух элементах из раздела Targets - WebDriverAgentLib и WebDriverAgentRunner. В выпадающем списке выберите любой сертификат разработчика с возможностью установки приложения на реальный девайс.
Для работы с симулятором подобный сертификат не обязателен.


## Как сделать colibri-ui лучше

Мы приветствуем участие в open source проектах. Для упрощения приема pull request'ов мы просим соблюдать простые правила:
1) Если мы пишем общие шаги, то в них не должно быть действий зависящих от приложения либо от особенностей платформы (например наботает только на одном девайсе).
2) Если предлагаются изменения в ядро - они должны быть покрыты тестами, и тесты должны проходить.
3) Стараемся делать PR с одним коммитом.

## Template

https://github.com/alfa-laboratory/colibri-ui-template

## Контакты

[Telegram](https://t.me/colibri_ui_community)
