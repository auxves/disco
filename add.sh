read -rp "URL: " URL
read -rp "ID: " ID
read -rp "Title: " TITLE
read -rp "Start: " START
read -rp "End: " END

[ -z "$URL" ] || [ -z "$ID" ] || [ -z "$TITLE" ] && exit 1

SOUNDS=src/main/resources/assets/disco/sounds
TEMP="$SOUNDS/$ID.tmp"

ASSETS=src/main/resources/assets/disco

youtube-dl "$URL" \
  --extract-audio \
  --audio-format vorbis \
  --quiet \
  -o "$TEMP.%(ext)s"

trim=()

[ -n "$START" ] && trim+=(-ss "00:$START")
[ -n "$END" ] && trim+=(-to "00:$END")

ffmpeg -i "$TEMP.ogg" \
  "${trim[@]}" \
  -ac 1 \
  -hide_banner -loglevel panic \
  "$SOUNDS/$ID.ogg"

rm -rf "$TEMP.ogg"

# Discs.kt
sed "/arrayOf/a \  \"$ID\"," -i src/main/kotlin/io/arnohovhannisyan/disco/Discs.kt

# sounds.json
sed "/^{/a \  \"$ID\": { \"category\": \"record\", \"sounds\": [{ \"name\": \"disco:$ID\", \"stream\": true }] }," -i "$ASSETS/sounds.json"

# lang
sed "/^{/a \  \"item.disco.$ID.desc\": \"$TITLE\"," -i "$ASSETS/lang/en_us.json"

# tag
sed "/values/a \    \"disco:$ID\"," -i src/main/resources/data/disco/tags/items/discs.json

# model
cat <<EOF > "$ASSETS/models/item/$ID.json"
{
  "parent": "item/generated",
  "textures": {
    "layer0": "disco:item/$ID"
  }
}
EOF