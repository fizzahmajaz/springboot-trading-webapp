const chartCtx = document.getElementById('chart').getContext('2d');
let priceChart = new Chart(chartCtx, {
    type: 'line',
    data: { labels: [], datasets: [{ label: 'BTC Price', data: [], borderColor: 'blue', fill: false }] },
    options: { responsive: true }
});

const socket = new WebSocket("ws://localhost:8080/ws/prices");
socket.onmessage = (event) => {
    const data = JSON.parse(event.data);
    priceChart.data.labels.push(new Date().toLocaleTimeString());
    priceChart.data.datasets[0].data.push(data.price);
    priceChart.update();
};

async function placeOrder() {
    const symbol = document.getElementById("symbol").value;
    const type = document.getElementById("orderType").value;
    const price = document.getElementById("price").value;
    const qty = document.getElementById("qty").value;

    const res = await fetch("/orders", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ symbol, type, price, qty })
    });
    alert(await res.text());
}

async function loadPortfolio() {
    const res = await fetch("/portfolio/1");
    document.getElementById("portfolioData").innerHTML = JSON.stringify(await res.json());
}
loadPortfolio();

async function loadLeaderboard() {
    const res = await fetch("/leaderboard");
    document.getElementById("leaderboardData").innerHTML = JSON.stringify(await res.json());
}
loadLeaderboard();

async function runBacktest() {
    const symbol = document.getElementById("symbol").value;
    const start = document.getElementById("start").value;
    const end = document.getElementById("end").value;

    const res = await fetch(`/backtest?symbol=${symbol}&start=${start}&end=${end}`);
    document.getElementById("backtestResult").innerHTML = JSON.stringify(await res.json());
}

async function calculateRisk() {
    const returns = [0.02, 0.01, -0.005, 0.03, -0.01];
    const res = await fetch("/risk", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(returns)
    });
    document.getElementById("riskMetrics").innerHTML = JSON.stringify(await res.json());
}