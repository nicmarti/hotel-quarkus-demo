<template>
    <div class="l-content">

            <HotelList v-if="hotels" :hotels="hotels"></HotelList>
            <p v-else>
                You do not have any Hotel in your database
            </p>

    </div>
</template>

<script>
    import HotelList from "@/components/HotelList";

    export default {
        components: {HotelList},
        name: "Hotels"
    }
</script>

<style scoped>
    .hotelBox {
        padding: 20px 30px;
        border: 1px solid #cfcfcf;
    }
</style>