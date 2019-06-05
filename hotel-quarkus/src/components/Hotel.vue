<template>
    <div class="hotelSimple">
        <h2><strong>{{hotel.title}}</strong></h2>
        <p>{{hotel.zip}}</p>
        <strong>{{hotel.price}}</strong>
        <router-link to="/hotels">Back to all hotels</router-link>
    </div>
</template>

<script>
    import axios from 'axios';

    export default {
        name: 'hotel',
        data() {
            return {
                hotel: {
                    name : "Loading"
                },
                errors:[]
            }
        },
        created() {
            let id = this.$route.params.id;

            axios.get(`http://localhost:8080/hotels/${id}`)
                .then(response => this.product = response.data[0])
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

