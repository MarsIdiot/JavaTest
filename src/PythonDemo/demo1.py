list=[11, 22, 33, 44, 55, 66, 77, 88, 99, 90]
tup={
    'k1':[],
    'k2':[]
}
for i in list:
    if i>66:
        tup['k1'].append(i)
    else:
        tup['k2'].append(i)

print (tup)