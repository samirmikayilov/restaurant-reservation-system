# 🏨 Restaurant Reservation System

Bu layihə **Spring Boot** əsasında hazırlanmış restoran rezervasiya
sistemidir. Frontend hissəsi HTML/CSS/JS şablonlardan ibarətdir və
 --- backend funksionallığını test
etmək üçün istifadə olunur.

------------------------------------------------------------------------

## 🚀 Funksionallıqlar

### 🔐 Authentication & Authorization

-   ✔️ User Register\ Email OTP ilə qeydiyyat
-   ✔️ User Login\
-   ✔️ JWT Token əsasında təhlükəsiz giriş\
-   ✔️ Logout\
-   ✔️ Role-Based Access Control (USER / ADMIN)

### 🪑 Admin Panel (ADMIN rolunda)

-   ✔️ Masa yaratmaq\
-   ✔️ Masaların siyahısını görmək\
-   🔜 Masaları yeniləmək və silmək

### 📅 Reservation Sistemi

-   ✔️ User masa rezerv edə bilir\
-   ✔️ User yalnız öz rezervlərini görə bilir\
-   ✔️ Admin bütün rezervləri görə bilir\
-   🔜 Rezerv təsdiqləmə / ləğv etmə

------------------------------------------------------------------------

## 🎨 Frontend (Hazır Şablon)

Frontend hissəsi hazır HTML/CSS/JS şablonu əsasında işləyir.\
Bu şablon yalnız test məqsədi ilə əlavə olunub və özüm yazmamışam.

Gələcəkdə: - 🔲 Custom frontend yazılacaq\
- 🔲 Login/Register UI\
- 🔲 Admin Panel UI\
- 🔲 Reservation UI

------------------------------------------------------------------------

## 🛠 Texnologiyalar

-   Java 17\
-   Spring Boot 3\
-   Spring Web\
-   Spring Security (JWT)\
-   Spring Data JPA\
-   MySQL\
-   Lombok\
-   ModelMapper\
-   HTML/CSS/JS (template)\
-   Docker (tezliklə)\
-   Kafka (tezliklə)

------------------------------------------------------------------------

## 📁 Layihəni İcra Etmək

### 1️⃣ Repo-nu klonla

    git clone https://github.com/samirmikayilov/restaurant-reservation-system.git

### 2️⃣ MySQL-də database yarat

``` sql
CREATE DATABASE restaurant_reservation;
```

### 3️⃣ Konfiqurasiyanı düzəlt

`application.properties`:

    spring.datasource.username=root
    spring.datasource.password=*****

### 4️⃣ Backend-i işə sal

    mvn spring-boot:run

------------------------------------------------------------------------

## 🧭 Gələcək Planlar (TODO)

-   Kafka ilə Notification Service\
-   Admin panel UI\
-   Docker Compose\
-   Full frontend inteqrasiya

------------------------------------------------------------------------

## 👤 Developer

**Samir Mikayılov**\
Java Backend Developer\
GitHub: https://github.com/samirmikayilov

------------------------------------------------------------------------
