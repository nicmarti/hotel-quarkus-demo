<template>
    <div class="bookingCounter">
        <div v-if="bookingCounter">This hotel was booked {{bookingCounter}} times</div>
        <div v-else>No recent booking for this hotel</div>
    </div>
</template>

<script>
    import axios from 'axios';

    export default {
        name: 'bookingCounter',
        props: {
            total: Number
        },
        data() {
            return {
                bookingCounter: this.total,
                errors: [],
            }
        }
    };
</script>

<style scoped>

</style>

