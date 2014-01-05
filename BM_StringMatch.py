def buildBc(pattern):
    bmBc = [-1 for x in range(0,256)]
    bmBcd = {}
    for idx, val in enumerate(pattern):
        bmBc[ord(val)] = idx
        
    print({(val,idx) for idx,val in enumerate(pattern) })
    return bmBc

def buildGs(pattern):
    index = 1;
    patternLen = len(pattern)
    bmGs = [0 for x in range(0,patternLen)]
    while(index < patternLen):
        beginIndex = index-1;
        found = False
        while( not found  and beginIndex >= 0):
            if(pattern[index : patternLen] == pattern[beginIndex : beginIndex+len(pattern)-index]):
                found = True
                if(beginIndex != 0 and pattern[beginIndex-1] == pattern[index-1]):
                    found = False
            beginIndex -= 1

        if(found):
            bmGs[index] = index - beginIndex - 1
        else:
            for tempIndex in range(index+1, patternLen):
                if(pattern[0:patternLen-tempIndex] == pattern[tempIndex:patternLen]):
                    bmGs[index] = tempIndex
                    break;
            else:
                bmGs[index] = patternLen
        index += 1
    bmGs[0] = bmGs[1]
    print(bmGs)
    return bmGs

def BMsearch(textStr,patternStr):
    text = list(textStr)
    pattern = list(patternStr)
    patternLen = len(pattern)
    textLen = len(text)
    if(patternLen == 0 or textLen < patternLen ):
        return []
    if(patternLen == 1):
        return [index for index,val in enumerate(text) if val == pattern[0]]

    currentIndex = patternLen - 1
    result = [];
    bmBc = buildBc(pattern)
    bmGs = buildGs(pattern)
    while(currentIndex < textLen):
        
        #print the compare process
        print(currentIndex - patternLen + 1 )
        print(textStr)
        builderStr = ""
        for i in range(0,currentIndex - patternLen + 1):
            builderStr += " "
        print(builderStr+patternStr)
        ###################################
        
        compareIndex = 0
        while(compareIndex < patternLen ):
            if(text[currentIndex - compareIndex] != pattern[patternLen-1-compareIndex]):
                break
            compareIndex += 1
        else:
            result.append([currentIndex - patternLen + 1,currentIndex])
            currentIndex += bmGs[0]
            continue
        step = patternLen-1-compareIndex - bmBc[ord(text[currentIndex - compareIndex])]
        if(compareIndex != 0):
            if(step < bmGs[patternLen-1-compareIndex+1]):
                step = bmGs[patternLen-1-compareIndex+1]
        currentIndex += step
        
    #print the compare process
    print(currentIndex - patternLen + 1 )
    print(textStr)
    builderStr = ""
    for i in range(0,currentIndex - patternLen + 1):
        builderStr += " "
    print(builderStr+patternStr)
    ###################################
    
    return result
         
print(BMsearch('abracadabtabradabracadabcadaxbrabbracadabraxxxxxxabracadabracadabra','abrbcwa'))

