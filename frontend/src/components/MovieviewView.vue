<template>

    <v-data-table
        :headers="headers"
        :items="movieview"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'MovieviewView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "movieId", value: "movieId" },
                { text: "desc", value: "desc" },
                { text: "reviewCnt", value: "reviewCnt" },
                { text: "movieStatus", value: "movieStatus" },
                { text: "rsvId", value: "rsvId" },
                { text: "rsvStatus", value: "rsvStatus" },
                { text: "customerId", value: "customerId" },
                { text: "numberOfSeats", value: "numberOfSeats" },
            ],
            movieview : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/movieviews'))

            temp.data._embedded.movieviews.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.movieview = temp.data._embedded.movieviews;
        },
        methods: {
        }
    }
</script>

