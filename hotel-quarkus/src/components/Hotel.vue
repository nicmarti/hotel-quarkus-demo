<template>
    <div class="hotelSimple pure-g">
        <div class="pure-u-1-3">
            <img v-bind:src="hotel.hotelImageURL" class="pure-image">
        </div>
        <div class="pure-u-1-3">
        <h2><strong>{{hotel.name}}</strong></h2>
        <p>{{hotel.zip}} {{hotel.city}}</p>
        <strong>{{hotel.price}} &euro;</strong>
        <p>{{hotel.description}}</p>
        </div>
        <div class="pure-u-1-3">
            <h4>Room amenities</h4>
            <ul>
                <li>Air conditioning</li>
                <li>Ensuite bathroom</li>
                <li>Bath</li>
                <li>Flat-scree TV</li>
                <li>Hairdryer</li>
                <li>Towels</li>
                <li>Mini-bar</li>
            </ul>
        </div>
        <div class="pure-u-2-3">
            <router-link to="/hotels" class="button-choose pure-button">Back to all hotels</router-link>
        </div>
    </div>
</template>

<script>
    import axios from 'axios';

    export default {
        name: 'hotel',
        data() {
            return {
                hotel: {
                    name : "Loading details..."
                },
                errors:[]
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
            })

        }
    };
</script>

<style scoped>
    .hotelSimple{
        padding: 20px 30px;
        border: 1px solid #cfcfcf;
    }
</style>

