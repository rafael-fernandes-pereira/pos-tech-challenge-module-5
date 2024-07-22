package:
	@./eureka/mvnw clean package -f ./eureka/pom.xml -DskipTests
	@./gateway/mvnw clean package -f ./gateway/pom.xml -DskipTests
	@./users/mvnw clean package -f ./users/pom.xml -DskipTests
	@./products/mvnw clean package -f ./products/pom.xml -DskipTests
	@./carts/mvnw clean package -f ./carts/pom.xml -DskipTests
	@./checkout/mvnw clean package -f ./checkout/pom.xml -DskipTests
	@./payment/mvnw clean package -f ./payment/pom.xml -DskipTests

run-docker: package
	@docker compose up