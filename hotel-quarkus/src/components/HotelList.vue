<template>
    <div class="pricing-tables pure-g">
        <div class="pure-u-1">
            <h3>Hotel list</h3>
            <p>Discover our nice and superior list of hotels : </p>
        </div>
        <div class="pure-u-1 pure-u-md-1-3" v-for="hotel in hotels" :key="hotel.id">
            <div class="pricing-table pricing-table-biz">
                <div class="pricing-table-header">
                    <h2>{{ hotel.name }}</h2>

                    <span class="pricing-table-price">
                        {{ hotel.price }} <span>price in &euro; for one night</span>
                    </span>


                    <img v-bind:src="hotel.hotelImageURL" class="pure-u-1 pure-image">
                </div>

                <ul class="pricing-table-list">
                    <li>{{ hotel.address }}</li>
                    <li>{{ hotel.zip }} {{ hotel.city }}</li>
                    <li>Valid for one standard room</li>
                </ul>

                <router-link :to="`/hotels/${hotel.hotelId}`" class="button-choose pure-button" >Details</router-link>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "HotelList",
        props: {hotels: {type: Array, required: true}},
    }
</script>

<style scoped>

</style>