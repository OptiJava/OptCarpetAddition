dir=$(ls -l ./versions | awk '/^d/ {print $NF}')
out_dir="./out"

for i in $dir
do
    tmp_dir="./versions/$i/build/libs/*.jar"
    cp $tmp_dir $out_dir
done
