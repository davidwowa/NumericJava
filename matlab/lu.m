format long;
A = [-2 0 3 -2; -4 -2 7 -6; 6 -6 -4 1; 2 0 1 6];

[L, U] = lu(A);

M0 = [1 0 0 0; 
     -2 1 0 0; 
      0 0 1 0; 
      0 0 0 1];

M1 = [1 0 0 0; 
      0 1 0 0; 
      3 0 1 0; 
      0 0 0 1];
  
M2 = [1 0 0 0; 
      0 1 0 0; 
      0 0 1 0; 
      1 0 0 1];
  
M3 = [1 0 0 0; 
      0 1 0 0; 
      0 -3 1 0; 
      0 0 0 1];
  
M4 = [1 0 0 0; 
      0 1 0 0; 
      0 0 1 0; 
      0 0 -2 1];
  
Uo = [-2 0 3 -2; 
      0 -2 1 -2; 
      0 0 2 1; 
      0 0 0 2];

  myL = (((M0 * M1) * M2) * M3) * M4;

  Atest = myL * Uo
  myL
  A/Uo