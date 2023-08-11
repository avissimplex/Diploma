Для запуска приложения и тестов нужно выполнить следующие шаги:  
1. Запускаем программу docker dekstop  
2. Запускаем в IDE в терминале файл docker-compose командой `docker-compose up`
3. Запускаем в другом окне терминала команду `java "-Dspring.datasource.url=jdbc:postgresql://localhost:5433/kuularbase" "-Dspring.datasource.username=kuular" "-Dspring.datasource.password=12345v" -jar aqa-shop.jar`
   (для windows с кавычками, для других ОС без кавычек) для постгресс или `java "-Dspring.datasource.url=jdbc:postgresql://localhost:3036/kuularbase" "-Dspring.datasource.username=kuular" "-Dspring.datasource.password=12345v" -jar aqa-shop.jar`

4. Запускаем тесты