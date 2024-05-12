#/usr/bin/bash

java -jar ../SlideRule.jar ./test_studentProf.txt ./result_studentProf.txt

if grep -q "s1.inCombat : true" ./result_studentProf.txt; then
    echo -e "\e[32mTEST-4 STUDENT PROF PASSED\e[0m"
else
    echo -e "\e[31mTEST-4 STUDENT PROF FAILED\e[0m"
fi

