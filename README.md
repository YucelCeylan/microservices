# Microservices

Örnek bir microservis sisteminin gerçekleştirimini içermektedir. 

## Servislerin Özellikleri

### Config-Eureka

Adından da anlaşılacağı gibi netflix'in eureka sunucusudur. *Naming Server* olarak çalışır ve sistemdeki diğer servislerin bu sunucuya kayıtlanmasını sağlayarak *Service Discovery* görevini yerine getirir.

Çalıştırıldıktan sonra *localhost:8761* üzerinden arayüzüne erişilebilir.

### Currency-Service

Kur çevrimi için örnek bir endpoint barındıran servistir. Config-Eureka'ya kayıtlanır ve Forex-Service'ten kur bilgilerini alır. Bilgileri alırken *OpenFeign* apisini kullanır. *Client Side Load Balancer* olarak *Ribbon* kullanılır.

Çalıştırıldıktan sonra *localhost:8100/swagger-ui.html* adresinden *Swagger* ara yüzüne erişilebilir.

### Forex-Service

Kur bilgilerini barındıran ve onları api olarak sunan servistir. Sistemde veritabanı erişimi olan tek servistir. Bunun için *JPA* ve *SpringData* apilerinden yararlanmaktadır.

Çalıştırıldıktan sonra *localhost:8000/swagger-ui.html* adresinden *Swagger* ara yüzüne erişilebilir. Forex-Service için birden fazla instance çalıştırılacak olursa Currency-Service tarafındaki *Load Balancer* bu instancelardan birini seçecektir.

## Servisler Arasındaki İlişki

![](C:\Users\Yucel\GitRepo\microservices\service_diagram.png)

