#/usr/bin/bash

java -jar ../SlideRule.jar ./test_profItem.txt ./result_profItem.txt

if grep -q "r2.items 0" ./result_profItem.txt; then
    echo -e "\e[32mTEST-1 PROF ITEM PASSED\e[0m"
else
    echo -e "\e[31mTEST-1 PROF ITEM FAILED\e[0m"
fi

