1000 GOTO 20
1010 I=0:FR=1:GOSUB 280
1020 HO=0:VE=0:GOSUB 110:PRINT I:I=I+1
1025 IF I=10000000 THEN 1050
1030 GOSUB 200:IF IN=0 THEN 1020
1040 HO=5:VE=5:GOSUB 110:PRINT IN$:GOTO 1020
1050 HO=9:VE=9:GOSUB 110:PRINT "End.":GOTO 950