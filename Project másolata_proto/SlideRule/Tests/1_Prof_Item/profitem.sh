#/usr/bin/bash

java -jar ../SlideRule.jar ./test_profItem.txt ./result_profItem.txt

if grep -q "r2.items 0" ./result_profItem.txt; then
    echo -e "\e[32mTEST PROFITEM PASSED\e[0m"
else
    echo -e "\e[31mTEST PROFITEM FAILED\e[0m"
fi

