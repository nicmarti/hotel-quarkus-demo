<template>
    <div class="bookingCounter">
        <div v-if="bookingCounter === 1">This hotel was booked one time</div>
        <div v-if="bookingCounter > 1">This hotel was booked {{bookingCounter}} times</div>
        <div v-if="bookingCounter < 1">No recent booking for this hotel</div>
    </div>
</template>

<script>
    import axios from 'axios';

    export default {
        name: 'bookingCounter',
        data() {
            return {
                bookingCounter: 0,
                errors: [],
            }
        },
        methods: {
            getCounter() {
                let id = this.$route.params.id;
                axios.get(`http://localhost:8080/bookings/hotel/${id}/counter`)
                    .then(response => {
                        this.bookingCounter = response.data;
                    })
                    .catch(e => {
                        this.errors.push(e)
                    });
            }
        },
        created() {
            this.getCounter()
        }
    };
</script>

<style scoped>

</style>

