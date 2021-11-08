<template>
  <v-dialog
    v-model="dialog"
    persistent
    max-width="290"
  >
    <template v-slot:activator="{ on, attrs }">
      <v-btn
        class="text-white"
        style="background-color: #439474;"
        v-bind="attrs"
        v-on="on"
      >
        {{ buttonText }}
      </v-btn>
    </template>
    <v-card>
      <v-card-title class="text-h5">
        {{ dialogTitle }}
      </v-card-title>
      <v-card-text>{{ dialogContent }}</v-card-text>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn
          style="color: #439474;"
          text
          @click="$emit('clickO')"
        >
          {{ buttonO }}
        </v-btn>
        <v-btn
          style="color: #7D7D7D;"
          text
          @click="dialog=false"
        >
          {{ buttonX }}
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
export default {
  name: 'Dialog',
  props: ['buttonText',
          'dialogTitle',
          'dialogContent',
          'buttonO',
          'buttonX'],
  data () {
    return {
      dialog: false
    }
  }
}
</script>

<style scoped>
.primary-color {
  background: #439474;
}
.secondary-color {
  color: #7D7D7D;
}
</style>