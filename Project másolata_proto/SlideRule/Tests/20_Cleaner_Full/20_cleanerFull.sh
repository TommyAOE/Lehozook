#/usr/bin/bash

java -jar ../SlideRule.jar ./test_cleanerFull.txt ./result_cleanerFull.txt

if grep -q "is full" ./result_cleanerFull.txt; then
    echo -e "\e[32mTEST-20 CLEANER FULL PASSED\e[0m"
else
    echo -e "\e[31mTEST-20 CLEANER FULL FAILED\e[0m"
fi

