Для запуска приложения и тестов нужно выполнить следующие шаги:  
1. Запускаем программу docker dekstop   
2. Запускаем в IDE в терминале файл docker-compose командой `docker-compose up`  
3. Запускаем в другом окне терминала java-приложение `aqa-shop.jar`  
а) для  MySQL `java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" "-Dspring.datasource.username=app" "-Dspring.datasource.password=pass" -jar aqa-shop.jar`  
   (для windows с кавычками, для других ОС без кавычек).    
б) для PostgresQL `java "-Dspring.datasource.url=jdbc:postgresql://localhost:5433/app" "-Dspring.datasource.username=app" "-Dspring.datasource.password=pass" -jar aqa-shop.jar`  
   Порт для PostgresQL выбран нестандартный, так как на моем компьютере стандартный уже занят.  
4. Запускаем тесты:  
а)  для MySQl `./gradlew test '-Ddb.dsn=jdbc:mysql://localhost:3306/app'`   
б) для PostgresQL `./gradlew test '-Ddb.dsn=jdbc:postgresql://localhost:5433/app'`  

