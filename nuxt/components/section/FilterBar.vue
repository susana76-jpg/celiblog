<template>
  <div class="filter-bar px-4 mb-12">

    <!-- TITLE ------------------------->
    <div class="filter-bar__title">
      <h3>Búsqueda</h3>
      <p class="py-1">{{ total }} resultados</p>
    </div>
    <!---------------------------------->

    <!-- SEARCH AND FILTER ------------->
    <v-text-field 
      clearable
      single-line
      hide-details
      prepend-inner-icon="mdi-magnify"
      variant="outlined"
      color="primary"
      base-color="primary"
      density="comfortable"
      class="main-search-input mb-1"
      :label="label" 
      :model-value="search"
      @update:modelValue="emit('update:search', $event)"
    />
    <!---------------------------------->

    <!-- CHIP GROUP -------------------->
    <v-chip-group
      v-if="showTags"
      filter
      multiple
      show-arrows
      selected-class="bg-primary text-white"
      v-model="type"
      @update:modelValue="emit('update:tag', $event)"
    >
      <v-chip
        v-for="tag in tags"
        :key="tag"
        :value="tag"
        variant="outlined"
        class="text-primary bg-white"
      >
        {{ tag }}
      </v-chip>
    </v-chip-group>
    <!---------------------------------->

  </div>
</template>

<script setup lang="ts">
const props = withDefaults(defineProps<{
  total: number;
  label: string;
  search?: string;
  tags?: string[];
  showTags?: boolean;
}>(), {
  showTags: true
});

const emit = defineEmits<{
  (e: 'update:tag', value: string[]): void;
  (e: 'update:search', value: string): void;
}>();

const type = ref<string[]>([]);
</script>

<style lang="scss">
.filter-bar {
  &__title {
    display: flex;
    align-items: center;
    margin-bottom: 15px;

    h3 {
      font-size: 28px;
      line-height: 26px;
      font-weight: 500;
      letter-spacing: 0.2px;
      color: #242424;
      margin-bottom: 5px;
      margin-right: 12px;
    }

    p {
      font-size: 18px;
      padding: 1px 20px;
      border-radius: 4px;
      background-color: #2424243f;
    }
  }

  .v-field__overlay {
    border: 2px solid #836A02 !important;
  }

  .v-chip.bg-primary {
    color: white !important;
  }
}
</style>
