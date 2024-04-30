#/usr/bin/bash

java -jar ../SlideRule.jar ./test_studentUseTVSZ.txt ./result_studentUseTVSZ.txt

if grep -q "r1.students 2" ./result_studentUseTVSZ.txt; then
    echo -e "\e[32mTEST-12 STUDENT USE TVSZ PASSED\e[0m"
else
    echo -e "\e[31mTEST-12 STUDENT USE TVSZ FAILED\e[0m"
fi

