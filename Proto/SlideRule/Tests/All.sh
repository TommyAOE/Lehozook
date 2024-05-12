#/usr/bin/bash

parent_folder="."
all_passed=0
failed_lines=()  # Initialize an empty array to store failed lines

while IFS= read -r -d '' directory; do
    cd "$directory" || exit 1
    while IFS= read -r -d '' script; do
        output=$(sh "$script")
        output=${output#-e}
        echo "$output"
        if echo "$output" | grep -q "FAILED"; then
            all_passed=1
            failed_lines+=("$output")  # Append failed line to the array
        fi
    done < <(find . -maxdepth 1 -type f -name "*.sh" -print0)
    cd - >/dev/null
done < <(find "$parent_folder" -mindepth 1 -maxdepth 1 -type d -print0 | sort -zV)

echo
if [ $all_passed -eq 0 ]; then
    echo -e "\e[32mALL PASSED\e[0m"  # Green color for "ALL PASSED"
else
    echo -e "\e[31mFAILED TESTS:\e[0m"  # Red color for "FAILED TESTS:"
    for line in "${failed_lines[@]}"; do
        echo "$line"
    done
fi