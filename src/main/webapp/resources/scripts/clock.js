const dayOfWeek = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"]
const months = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];

window.addEventListener("DOMContentLoaded", () => {
    let clockFromDoc = document.getElementById("clock");
    let dayFromDoc = document.getElementById("dayOfWeek");
    let setTime = () => {
        let date = new Date();

        let time = [date.getHours(), date.getMinutes(), date.getSeconds()]; // |[0] = Hours| |[1] = Minutes| |[2] = Seconds|

        if (time[0] < 10) {
            time[0] = "0" + time[0];
        }
        if (time[1] < 10) {
            time[1] = "0" + time[1];
        }
        if (time[2] < 10) {
            time[2] = "0" + time[2];
        }

        clockFromDoc.innerHTML = [time[0], time[1], time[2]].join(':');
        dayFromDoc.innerHTML = dayOfWeek[date.getDay()] + " " + date.getDate() + " " + months[date.getMonth()] + " " + date.getFullYear();
    }
    setTime()
    setInterval(setTime, 10000);
})