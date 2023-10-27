window.addEventListener("DOMContentLoaded", () => {
    let xButtons = document.querySelectorAll('.x-button');
    xButtons.forEach(function(button) {
        button.addEventListener('click', event=>{
            xButtons.forEach(function(button) {
                button.classList.remove('active');
            });
            event.target.classList.add('active');
        });
    });

    let rButtons = document.querySelectorAll('.r-link');
    rButtons.forEach(function(button) {
        button.addEventListener('click', event=>{
            rButtons.forEach(function(button) {
                button.classList.remove('active');
            });
            event.target.classList.add('active');
        });
    });
})

function findButtonByValue(x){
    let xButtons = document.querySelectorAll('.x-button');xButtons.forEach(function(button) {
        for (let i = 0; i < xButtons.length; i++) {
                if (button.value === x.toString()) {
                    button.click()
                    break
                }
        }
    });
}
function checkButtonColor(x){
    let xButtons = document.querySelectorAll('.x-button');xButtons.forEach(function(button) {
        for (let i = 0; i < xButtons.length; i++) {
            if (button.value === x.toString()) {
                button.classList.add('active');
                break
            }
        }
    });
}
