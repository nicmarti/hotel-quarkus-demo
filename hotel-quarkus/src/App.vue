<template>
    <div id="app">

        <div class="pure-menu pure-menu-horizontal">
            <a href="#" class="pure-menu-heading">Quarkus Hotel</a>
            <ul class="pure-menu-list">
                <li class="pure-menu-item"><a href="/" class="pure-menu-link">Home</a></li>
                <li class="pure-menu-item pure-menu-selected"><a href="#" class="pure-menu-link">Hotels</a></li>
                <li class="pure-menu-item"><a href="/" class="pure-menu-link">Booking</a></li>
            </ul>
        </div>

        <div class="banner">
            <h1 class="banner-head">
                Quarkus demo<br>
                <small>Java + Vue.JS backend</small>
            </h1>
        </div>

        <router-view :hotels="theHotels" class="hotels"></router-view>

        <div class="footer l-box">
            <p>
                <img src="logo_quarkus.png" alt="Logo Quarkus" class="pure-image" style="width: 64px"><br>
                <a href="https://quarkus.io/" title="Quarkus web site">Build with Quarkus</a> by Nicolas Martignole - Lunatech
            </p>
        </div>
    </div>
</template>

<script>
    import axios from 'axios';

    export default {
        name: 'app',
        data() {
            return {
                theHotels: []
            };
        },
        components: {
        },
        created: function () {
            axios.get('http://localhost:8080/hotels')
                .then(response => this.theHotels = response.data)
        }
    }
</script>

<style>
    #app {
    }
</style>
