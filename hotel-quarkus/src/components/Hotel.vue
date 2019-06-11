<template>
    <div class="hotelSimple pure-g">

        <div class="pure-u-1-3">
            <h1><strong>{{hotel.name}}</strong></h1>
            <p>{{hotel.zip}} {{hotel.city}}</p>
            <strong>{{hotel.price}} &euro;</strong>
            <p>{{hotel.description}}</p>
        </div>
        <div class="pure-u-1-3">
            <h2>Room amenities</h2>
            <ul>
                <li>Air conditioning</li>
                <li>Ensuite bathroom</li>
                <li>Bath</li>
                <li>Flat-scree TV</li>
                <li>Hairdryer</li>
                <li>Towels</li>
                <li>Mini-bar</li>
            </ul>
            <h4>Book a room</h4>
            <form id="bookingForm"
                  @submit="checkForm"
                  class="pure-form pure-form-aligned"
            >
                <fieldset>
                    <div v-if="formErrors.length" class="error">
                        <b>Please correct the following error(s):</b>
                        <ul>
                            <li v-for="error in formErrors">{{ error }}</li>
                        </ul>
                    </div>
                    <input type="hidden" name="hotelId" v-bind:value="hotel.hotelId">
                    <div class="pure-control-group">
                        <label for="checkin">Check-in date</label>
                        <input id="checkin" v-model="checkin" type="date" name="checkinDate" value="2019-06-11">
                    </div>
                    <div class="pure-control-group">
                        <label for="checkout">Check-out date</label>
                        <input id="checkout" v-model="checkout" type="date" name="checkoutDate" value="2019-06-12">
                    </div>
                    <div class="pure-controls">
                        <input type="submit" value="Check availability" class="pure-button pure-button-primary">
                    </div>
                </fieldset>

            </form>

        </div>
        <div class="pure-u-1-3">
            <img v-bind:src="hotel.hotelImageURL" class="pure-image">
            <bookingCounter>Compteur</bookingCounter>
        </div>

        <div class="pure-u-2-3">
            <router-link to="/hotels" class="button-choose pure-button">Back to all hotels</router-link>
        </div>
    </div>
</template>

<script>
    import bookingCounter from '@/components/BookingCounter';
    import axios from 'axios';

    export default {
        name: 'hotel',
        components: {bookingCounter},
        data() {
            return {
                hotel: '',
                bookingCounterVal: 0,
                errors: [],
                formErrors: [],
                checkin: null,
                checkout: null,
            }
        },
        created() {
            let id = this.$route.params.id;

            axios.get(`http://localhost:8080/hotels/${id}`)
                .then(response => {
                    this.hotel = response.data;
                })
                .catch(e => {
                    this.errors.push(e)
                });
        },
        methods: {
            checkForm: function (e) {
                this.formErrors = [];
                e.preventDefault();
                let currentObj = this;
                let hotelId = this.$route.params.id;

                if (this.checkin && this.checkout) {
                    this.formErrors.push('sending booking request...');
                    // This has to map BookingRequest dto
                    let bookingRequest = {"checkinDate":this.checkin,"checkoutDate":this.checkout};
                    axios.post(
                        'http://localhost:8080/bookingService/hotel/' + hotelId, bookingRequest
                    ).then(function (response) {
                        currentObj.formErrors.push(response.data);
                    }).catch(function (error) {
                        currentObj.formErrors.push(error);
                    });
                }
                if (!this.checkin) {
                    this.formErrors.push('Checkin date required.');
                }
                if (!this.checkout) {
                    this.formErrors.push('Checkout date required.');
                }
                return false;
            }
        }
    };
</script>

<style scoped>
    .hotelSimple {
        padding: 20px 30px;
        border: 1px solid #cfcfcf;
    }
</style>

