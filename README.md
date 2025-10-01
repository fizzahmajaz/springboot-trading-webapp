# ⚡ Real-Time Stock/Crypto Trading Simulator  

![Trading Animation](https://media0.giphy.com/media/v1.Y2lkPTc5MGI3NjExY3ZmbzRpdzJzbGc2Z3Z1NTRrbmRpenE1OHhvOTJ2MG03YzJ2MnprNSZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9cw/TVbnJd2GvMPHHeSiD7/giphy.gif) 

A **real-time trading simulator** built with **Spring Boot + MySQL + HTML/CSS/JavaScript**.  
Streams **live Binance prices**, allows **paper trading**, tracks **portfolio & P&L**, and ranks users on a **leaderboard**.  

---

## 🚀 Features  

- 📈 **Live Price Feed** (via Binance WebSocket API)  
- 🛒 **Market & Limit Orders** (Buy/Sell)  
- 🔄 **Matching Engine (MVP)** — instant fills or simulated order book  
- 💰 **Portfolio Tracking** — balance, holdings, unrealized P&L  
- 📜 **Trade History** — every executed trade stored in DB  
- 🏆 **Leaderboard** — ranks users by performance  
- 🔔 **Real-Time Updates** via WebSocket  

---

## 🏗️ Tech Stack  

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)  
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)  
![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)  
![WebSocket](https://img.shields.io/badge/WebSocket-010101?style=for-the-badge&logo=socket.io&logoColor=white)  
![Binance](https://img.shields.io/badge/Binance-F3BA2F?style=for-the-badge&logo=binance&logoColor=black)  

**Backend:** Spring Boot, JPA, MySQL, Redis *(optional)*, Binance WebSocket API  
**Frontend:** HTML / CSS / JavaScript, STOMP.js, SockJS, Chart.js  

---

## 📂 Project Structure  

```
trading-simulator/
│── src/main/java/com/example/trading/
│   ├── entity/        # Entities: User, Order, Trade, Portfolio, Price
│   ├── repository/    # JPA Repositories
│   ├── service/       # Business logic (Orders, Matching Engine, Price Feed)
│   ├── controller/    # REST APIs (Users, Orders, Portfolio, Leaderboard)
│   └── config/        # WebSocket & Security Config
│
│── src/main/resources/
│   ├── application.properties # DB & WebSocket configs
│   └── static/                # HTML, CSS, JS frontend
│
└── README.md
```

---

## ⚙️ Setup  

### 1. Clone the Repo  
```bash
git clone https://github.com/your-username/trading-simulator.git
cd trading-simulator
```

### 2. Configure Database  
Edit `src/main/resources/application.properties`:  
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/tradingdb
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 3. Run the Backend  
```bash
mvn spring-boot:run
```

Backend runs on:  
```
http://localhost:8080
```

### 4. Open the Frontend  
Open `src/main/resources/static/index.html` in your browser.  

---

## 🔥 API Endpoints  

### Users  
- `POST /users/create` → Register new user  
- `GET /users/all` → List all users  

### Orders  
- `POST /orders/place/{userid}` → Place new order  
- `GET /orders/open/{symbol}` → Get open orders for a symbol  
- `POST /orders/cancel/{id}` → Cancel an order  

### Portfolio  
- `GET /portfolio/{userId}` → Get user portfolio  

### Leaderboard  
- `GET /leaderboard` → Get top performing users  

---

## 📊 Roadmap  

- ✅ Users + Orders  
- ✅ Trades + Portfolio  
- ✅ Leaderboard  
- ✅ Live Binance Price Feed  
- 🔜 Advanced Matching Engine (limit order book)  
- 🔜 Replay Mode & Backtesting  
- 🔜 Social Features (Copy-Trading, Strategy Sharing)  

---

## 🎥 Demo Preview  

![Portfolio Demo](https://media.giphy.com/media/3o7TKtnuHOHHUjR38Y/giphy.gif)  

---

## 🤝 Contributing  

Pull requests are welcome! For major changes, please open an issue first to discuss what you’d like to change.  

---

## 📜 License  

This project is licensed under the MIT License.  
