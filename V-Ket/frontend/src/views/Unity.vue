<template>
  <div id="unity">
    <div id="unity-container">
      <div id="unity-game-container">
        <div id="game-container"></div>
      </div>
    </div>
  </div>
</template>

<script>
export default {

}
</script>

<style scoped>
#unity{
  display: flex;
  overflow: hidden;
  flex-direction: column;
  -ms-user-select: none; 
  -moz-user-select: -moz-none;
  -khtml-user-select: none;
  -webkit-user-select: none;
  user-select: none;
}
#unity-container{
  display: flex;
  flex-direction: row;
  flex-wrap: nowrap;
  justify-content: center;
  align-items: stretch;
  width : 66.6vw;
  height : 100%;
  margin : 0 auto;
  text-align: center;
  flex : 1;
}
#unity-game-container{
  width : 100%;
}
#unity-main{
  width: 100%;
  flex-grow: 9;
  flex-basis: 0;
}
</style>