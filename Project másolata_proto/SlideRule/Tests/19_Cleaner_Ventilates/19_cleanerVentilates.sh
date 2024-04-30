#/usr/bin/bash

java -jar ../SlideRule.jar ./test_cleanerVentilates.txt ./result_cleanerVentilates.txt

if grep -q "r2.gas : NULL" ./result_cleanerVentilates.txt; then
    echo -e "\e[32mTEST-19 CLEANER VENTILATES PASSED\e[0m"
else
    echo -e "\e[31mTEST-19 CLEANER VENTILATES FAILED\e[0m"
fi

