#/usr/bin/bash

java -jar ../SlideRule.jar ./test_studentDies.txt ./result_studentDies.txt

if grep -q "r1.students 0" ./result_studentDies.txt; then
    echo -e "\e[32mTEST-8 STUDENT DIES PASSED\e[0m"
else
    echo -e "\e[31mTEST-8 STUDENT DIES FAILED\e[0m"
fi

