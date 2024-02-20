dir=$(ls -l ./versions | awk '/^d/ {print $NF}')

# create dirs
mkdir processed_artifacts
for i in $dir
do
    tmp_dir="./processed_artifacts/$i"
    mkdir "$tmp_dir"
done

# sort files
for i in $dir
do
    dst_dir="processed_artifacts/$i"
    find "artifacts" -type f -name "*$i*.jar" -exec mv {} "$dst_dir" \;
done
