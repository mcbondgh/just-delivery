//this is where all our javaScripts will seat.

let customerName = "JUST DELIVERY";
let customerMail = "customer@customer.com";
let paymentAmount = 50 * 1000;

console.log(`${customerName} is loaded`);

let makePayment = ()=> {
    alert(`Application payment is done by ${customerName} and amount is ${paymentAmount}`);
}

// function initMap() {
//     var mapOptions = {
//         center: { lat: 37.7749, lng: -122.4194 },
//         zoom: 12
//     };
//     var map = new google.maps.Map(document.getElementById('map'), mapOptions);
// }
