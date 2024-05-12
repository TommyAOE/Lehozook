#/usr/bin/bash

java -jar ../SlideRule.jar ./test_clean.txt ./result_clean.txt

if grep -q "picked up" ./result_clean.txt; then
    echo -e "\e[32mTEST-18 CLEAN PASSED\e[0m"
else
    echo -e "\e[31mTEST-18 CLEAN FAILED\e[0m"
fi

