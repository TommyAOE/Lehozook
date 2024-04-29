#/usr/bin/bash

java -jar ../SlideRule.jar ./test_studentStunnedProf.txt ./result_studentStunnedProf.txt

if grep -q "s1.inCombat : false" ./result_studentStunnedProf.txt; then
    echo -e "\e[32mTEST-5 STUDENT STUNNED PROF PASSED\e[0m"
else
    echo -e "\e[31mTEST-5 STUDENT STUNNED PROF FAILED\e[0m"
fi

