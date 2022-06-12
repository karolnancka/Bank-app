const operationType = document.getElementsByTagName("select")[0];
const accountFrom = document.getElementsByTagName("div")[1];
const accountTo = document.getElementsByTagName("div")[2];
const fromCurrency = document.getElementsByTagName("div")[4]
const toCurrency = document.getElementsByTagName("div")[5];

operationType.addEventListener("change", ()=>{

    // Transfer - 1
    // Deposit - 2
    // Withdrawal - 3
    // Exchange - 4

    if(operationType.value === '2') {
        accountFrom.classList.add("hidden");
        toCurrency.classList.add("hidden");
        fromCurrency.children[0].innerHTML = "Currency"

    } else if (operationType.value === '1') {
        accountTo.classList.remove("hidden");
        toCurrency.classList.remove("hidden")
        fromCurrency.children[0].innerHTML = "Base Currency"

    } else if(operationType.value === '3') {
        accountTo.classList.add("hidden");
        toCurrency.classList.add("hidden");
        fromCurrency.children[0].innerHTML = "Currency";

    } else if (operationType.value === '4') {
        accountTo.classList.add("hidden");
        toCurrency.classList.remove("hidden");
        fromCurrency.children[0].innerHTML = "Base Currency"
        accountTo.classList.add("hidden");
    }
});



